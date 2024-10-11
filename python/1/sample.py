from flask import Flask, request, make_response, redirect, jsonify
from Crypto.Cipher import AES
from Crypto.Random import get_random_bytes
from Crypto.Util.Padding import pad, unpad
from base64 import b64decode, b64encode
from json import loads

app = Flask(__name__)
cipher = AES.new(get_random_bytes(16), AES.MODE_ECB)

users = [{'usrid': 0, 'name': 'admin', 'pwd': get_random_bytes(16).hex()},
         {'usrid': 1, 'name': 'guest', 'pwd': 'guest'}]

def gen_cookie(usrid, name):
    name = name.replace('"', '')
    return b64encode(cipher.encrypt(pad(f'{{"usrid":{int(usrid)}, "name":"{name}"}}'.encode(), 16)))

@app.route('/settings', methods=['POST'])
def settings():
    user = loads(unpad(cipher.decrypt(b64decode(request.cookies.get('session'))), 16))
    if user:
        resp = make_response(redirect('/settings'))
        name = request.form.get('name').replace('"', '')
        for u in users:
            if u['usrid'] == user['usrid']: u['name'] = name
        resp.set_cookie('session', gen_cookie(user['usrid'], name))
        return resp
    return redirect('/login')

@app.route('/login', methods=['POST'])
def login():
    user = [u for u in users if u['name'] == request.form.get('name') and u['pwd'] == request.form.get('pwd')]
    if len(user) == 1:
        resp = make_response(redirect('/settings'))
        resp.set_cookie('session', gen_cookie(user[0]['usrid'], user[0]['name']))
        return resp
    return jsonify({'error': 'invalid credentials'})

if __name__ == '__main__':
    app.run(host='0.0.0.0')
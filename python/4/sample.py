import os
from tempfile import TemporaryDirectory
from subprocess import run, DEVNULL, PIPE
from flask import Flask, make_response, request

app = Flask(__name__)
def _git(cmd, args, cwd='/'):
    proc = run(['git', cmd, *args], stdout=PIPE, stderr=DEVNULL, cwd=cwd, timeout=5)
    return proc.stdout.decode().strip()

@app.route('/blame', methods=['POST'])
def blame():
    url = request.form.get('url', 'https://github.com/package-url/purl-spec.git')
    what = request.form.getlist('what[]')
    with TemporaryDirectory() as local:
        if not url.startswith(('https://', 'http://')):
            return make_response('Invalid url!', 403)
        _git('clone', ['--', url, local])
        res = []
        for i in what:
            file, lines = i.split(':')
            res.append(_git('blame', ['-L', lines, file], local))
        return make_response('\n'.join(res), 200)
    
@app.route('/test')
def test():
    res = 200 if os.path.exists('/pwned') else 404
    return make_response('', res)

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')
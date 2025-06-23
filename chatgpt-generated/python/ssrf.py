from flask import Flask, request
import requests

app = Flask(__name__)

@app.route('/fetch')
def fetch():
    url = request.args.get('url', '')
    try:
        r = requests.get(url)
        return r.text
    except:
        return "Error fetching URL"

if __name__ == '__main__':
    app.run(debug=True)

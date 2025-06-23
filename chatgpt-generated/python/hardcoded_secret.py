import flask

app = flask.Flask(__name__)

API_SECRET = "SuperSecretKey123" 

@app.route('/secret')
def get_secret():
    return f"The API secret is: {API_SECRET}"

if __name__ == '__main__':
    app.run(debug=True)

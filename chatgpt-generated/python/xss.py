from flask import Flask, request

app = Flask(__name__)

@app.route('/comment')
def comment():
    user_comment = request.args.get('comment', '')
    return f"<h2>User Comment:</h2><p>{user_comment}</p>"

if __name__ == '__main__':
    app.run(debug=True)

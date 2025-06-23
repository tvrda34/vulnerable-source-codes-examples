from flask import Flask, request

app = Flask(__name__)

fake_users = {
    '1': 'Alice',
    '2': 'Bob',
    '3': 'Charlie'
}

@app.route('/profile')
def profile():
    user_id = request.args.get('id')
    return f"User profile: {fake_users.get(user_id, 'Not found')}"

if __name__ == '__main__':
    app.run(debug=True)

import sqlite3
from flask import Flask, request

app = Flask(__name__)
conn = sqlite3.connect('users.db', check_same_thread=False)
cursor = conn.cursor()
cursor.execute("CREATE TABLE IF NOT EXISTS users (username TEXT, password TEXT)")
cursor.execute("INSERT INTO users VALUES ('admin', 'adminpass')")
conn.commit()

@app.route('/login')
def login():
    username = request.args.get('username', '')
    query = f"SELECT * FROM users WHERE username = '{username}'"
    result = cursor.execute(query).fetchall()
    return str(result)

if __name__ == '__main__':
    app.run(debug=True)

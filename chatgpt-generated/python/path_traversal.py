from flask import Flask, request

app = Flask(__name__)

@app.route('/read')
def read():
    filename = request.args.get('file', '')
    try:
        with open(filename, 'r') as f:
            return f.read()
    except:
        return "File not found or error"

if __name__ == '__main__':
    app.run(debug=True)

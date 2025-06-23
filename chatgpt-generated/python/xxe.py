import xml.etree.ElementTree as ET
from flask import Flask, request

app = Flask(__name__)

@app.route("/upload", methods=["POST"])
def upload():
    xml_data = request.data
    try:
        root = ET.fromstring(xml_data)  # XXE moguć ovde
        return f"Root tag: {root.tag}"
    except Exception as e:
        return f"Error parsing XML: {e}", 400

if __name__ == "__main__":
    app.run(debug=True)

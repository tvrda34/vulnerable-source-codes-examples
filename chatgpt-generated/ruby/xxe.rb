require 'sinatra'
require 'rexml/document'
include REXML

post '/upload' do
  xml = request.body.read
  doc = Document.new(xml)
  "Root element: #{doc.root.name}"
end

require 'sinatra'

get '/read' do
  file = params['file']
  begin
    content = File.read(file)
    "<pre>#{content}</pre>"
  rescue
    "Cannot read file."
  end
end

require 'sinatra'
require 'open-uri'

get '/fetch' do
  url = params['url']
  begin
    content = URI.open(url).read
    "<pre>#{content}</pre>"
  rescue => e
    "Error: #{e}"
  end
end

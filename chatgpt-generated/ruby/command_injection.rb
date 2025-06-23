require 'sinatra'

get '/ping' do
  host = params['host']
  result = `ping -c 1 #{host}`
  "<pre>#{result}</pre>"
end

require 'sinatra'

get '/xss' do
  comment = params['comment']
  "<h2>User Comment:</h2><p>#{comment}</p>"
end

require 'sinatra'

SECRET = "HardcodedSecretKey123"

get '/secret' do
  "The API Secret is: #{SECRET}"
end

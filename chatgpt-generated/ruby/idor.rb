require 'sinatra'

USERS = {
  "1" => "Alice",
  "2" => "Bob",
  "3" => "Charlie"
}

get '/profile' do
  id = params['id']
  "User Profile: #{USERS[id] || 'Not found'}"
end

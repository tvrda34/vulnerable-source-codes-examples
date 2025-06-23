require 'sinatra'
require 'sqlite3'

db = SQLite3::Database.new 'users.db'
db.execute 'CREATE TABLE IF NOT EXISTS users (username TEXT, password TEXT)'
db.execute 'INSERT INTO users (username, password) VALUES (?, ?)', ['admin', 'adminpass']

get '/login' do
  username = params['username']
  query = "SELECT * FROM users WHERE username = '#{username}'"
  result = db.execute(query)

  "Query: #{query}<br>" +
  result.map { |row| "User: #{row[0]}" }.join("<br>")
end

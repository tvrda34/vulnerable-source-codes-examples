<?php
$db = new SQLite3('users.db');
$db->exec("CREATE TABLE IF NOT EXISTS users (username TEXT, password TEXT)");
$db->exec("INSERT INTO users (username, password) VALUES ('admin', 'adminpass')");

$username = $_GET['username'] ?? '';
$query = "SELECT * FROM users WHERE username = '$username'";
$result = $db->query($query);

echo "<h2>Query:</h2> <pre>$query</pre>";
echo "<h2>Result:</h2>";
while ($row = $result->fetchArray()) {
    echo "Username: " . $row['username'] . "<br>";
}
?>

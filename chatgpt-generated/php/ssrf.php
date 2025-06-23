<?php
$url = $_GET['url'] ?? '';
$response = @file_get_contents($url);
echo "<pre>" . htmlspecialchars($response) . "</pre>";
?>

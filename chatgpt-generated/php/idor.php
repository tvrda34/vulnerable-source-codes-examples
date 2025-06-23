<?php
$users = [
    '1' => 'Alice',
    '2' => 'Bob',
    '3' => 'Charlie'
];

$id = $_GET['id'] ?? '';
echo "User Profile: " . ($users[$id] ?? 'Not found');
?>

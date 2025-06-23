<?php
$file = $_GET['file'] ?? '';
if (file_exists($file)) {
    echo "<pre>" . htmlspecialchars(file_get_contents($file)) . "</pre>";
} else {
    echo "File not found.";
}
?>

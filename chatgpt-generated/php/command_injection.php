<?php
$host = $_GET['host'] ?? '';
$output = shell_exec("ping -c 1 $host");
echo "<pre>$output</pre>";
?>

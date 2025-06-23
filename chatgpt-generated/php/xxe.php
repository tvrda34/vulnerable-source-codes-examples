<?php
libxml_disable_entity_loader(false); 
$xml = file_get_contents('php://input');

$dom = new DOMDocument();
$dom->loadXML($xml, LIBXML_NOENT | LIBXML_DTDLOAD);
echo "<pre>" . htmlspecialchars($dom->saveXML()) . "</pre>";
?>

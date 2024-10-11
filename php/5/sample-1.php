<?php
define('UPLOAD_FOLDER',
       sys_get_temp_dir().DIRECTORY_SEPARATOR.'uploads'.DIRECTORY_SEPARATOR);

function validateFilePath($fpath) {
  // Prevent path traversal
  if (str_contains($fpath, '..'.DIRECTORY_SEPARATOR)) {
    http_response_code(403);
    die('invalid path!');
  }
}

function uploadFile($src, $dest) {
  $path = dirname($dest);
  if (!file_exists($path)) {
    mkdir($path, 0755, true);
  }
  move_uploaded_file($src, $dest);
}

function normalizeFilePath($fpath) {
  if (strpos($_SERVER['HTTP_USER_AGENT'], 'Windows')) {
    return str_replace('\\', '/', $fpath);
  }
  return $fpath;
}

$src  = $_FILES['file']['tmp_name'];
$dest = UPLOAD_FOLDER.$_FILES['file']['full_path'];
validateFilePath($dest);
uploadFile($src, normalizeFilePath($dest));
echo 'file uploaded!';

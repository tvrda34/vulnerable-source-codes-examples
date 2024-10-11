<?php

session_start();

function generatePasswordResetToken($user) 
{
    $db = new SQLite3('/srv/users.sqlite', SQLITE3_OPEN_READWRITE);
    $token = md5(mt_rand(1, 100) . $user . time() . session_id());
    $p = $db->prepare('UPDATE users SET reset_token = :token WHERE name = :user');
    $p->bindValue(':user', $user, SQLITE3_TEXT);
    $p->bindValue(':token', $token, SQLITE3_TEXT);
    $p->execute();
}

package main

import (
	"database/sql"
	"fmt"
	"log"
	"net/http"
	_ "github.com/mattn/go-sqlite3"
)

func main() {
	db, _ := sql.Open("sqlite3", "./users.db")
	db.Exec("CREATE TABLE IF NOT EXISTS users (username TEXT, password TEXT)")
	db.Exec("INSERT INTO users (username, password) VALUES ('admin', 'adminpass')")

	http.HandleFunc("/login", func(w http.ResponseWriter, r *http.Request) {
		username := r.URL.Query().Get("username")
		query := fmt.Sprintf("SELECT * FROM users WHERE username = '%s'", username)
		fmt.Fprintf(w, "Query: %s\n", query)

		rows, err := db.Query(query)
		if err != nil {
			http.Error(w, "Query failed", 500)
			return
		}
		defer rows.Close()

		for rows.Next() {
			var u, p string
			rows.Scan(&u, &p)
			fmt.Fprintf(w, "User: %s\n", u)
		}
	})

	log.Fatal(http.ListenAndServe(":8080", nil))
}

package main

import (
	"fmt"
	"net/http"
)

var users = map[string]string{
	"1": "Alice",
	"2": "Bob",
	"3": "Charlie",
}

func main() {
	http.HandleFunc("/profile", func(w http.ResponseWriter, r *http.Request) {
		id := r.URL.Query().Get("id")
		fmt.Fprintf(w, "Profile: %s", users[id])
	})
	http.ListenAndServe(":8080", nil)
}

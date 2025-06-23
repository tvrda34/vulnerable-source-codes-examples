package main

import (
	"fmt"
	"net/http"
)

func main() {
	http.HandleFunc("/xss", func(w http.ResponseWriter, r *http.Request) {
		comment := r.URL.Query().Get("comment")
		fmt.Fprintf(w, "<h2>User Comment:</h2><p>%s</p>", comment)
	})
	http.ListenAndServe(":8080", nil)
}

package main

import (
	"fmt"
	"net/http"
	"os"
)

func main() {
	http.HandleFunc("/read", func(w http.ResponseWriter, r *http.Request) {
		file := r.URL.Query().Get("file")
		data, err := os.ReadFile(file)
		if err != nil {
			http.Error(w, "Cannot read file", 500)
			return
		}
		fmt.Fprintf(w, "<pre>%s</pre>", data)
	})
	http.ListenAndServe(":8080", nil)
}

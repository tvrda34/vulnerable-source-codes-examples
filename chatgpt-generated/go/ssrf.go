package main

import (
	"io"
	"net/http"
)

func main() {
	http.HandleFunc("/fetch", func(w http.ResponseWriter, r *http.Request) {
		url := r.URL.Query().Get("url")
		resp, err := http.Get(url)
		if err != nil {
			w.Write([]byte("Error fetching URL"))
			return
		}
		defer resp.Body.Close()
		io.Copy(w, resp.Body)
	})
	http.ListenAndServe(":8080", nil)
}

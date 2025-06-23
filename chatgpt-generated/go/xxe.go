package main

import (
	"encoding/xml"
	"fmt"
	"io"
	"net/http"
)

type Root struct {
	XMLName xml.Name
	Content string `xml:",innerxml"`
}

func main() {
	http.HandleFunc("/upload", func(w http.ResponseWriter, r *http.Request) {
		var root Root
		data, _ := io.ReadAll(r.Body)
		xml.Unmarshal(data, &root)
		fmt.Fprintf(w, "Parsed content: %s", root.Content)
	})
	http.ListenAndServe(":8080", nil)
}

package main

import (
	"fmt"
	"net/http"
)

const SECRET = "TopSecretAPIKey123"

func main() {
	http.HandleFunc("/secret", func(w http.ResponseWriter, r *http.Request) {
		fmt.Fprintf(w, "The API secret is: %s", SECRET)
	})
	http.ListenAndServe(":8080", nil)
}

package main

import (
	"net/http"
	"os/exec"
)

func main() {
	http.HandleFunc("/ping", func(w http.ResponseWriter, r *http.Request) {
		host := r.URL.Query().Get("host")
		out, _ := exec.Command("sh", "-c", "ping -c 1 "+host).CombinedOutput()
		w.Write(out)
	})
	http.ListenAndServe(":8080", nil)
}

// I found this URL parser on stackoverflow, it should be good enough
bool validate_domain(const char *url) {
    char domain[100];
    int port = 80;
    sscanf(url, "https://%99[^:]:%99d/", domain, &port);
    return strcmp("internal.service", domain) == 0;
}

int main(int argc, char **argv) {
  // [...]
  const char *url = argv[1];

  if (!validate_domain(url)) {
    printf("validate_domain failed\n");
    return 1;
  }

  if ((curl = curl_easy_init())) {
    curl_easy_setopt(curl, CURLOPT_URL, url);
    res = curl_easy_perform(curl); 
    switch(res) {
    case CURLE_OK:
        printf("All good!\n");
        break;
    default:
        printf("Nope :(\n");
        break;
    }
  }
  // [...]
}

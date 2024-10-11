<!-- oauth-popup.html -->
<script>
const handlers = Object.assign(Object.create(null), {
  getAuthCode(sender) {
    sender.postMessage({
      type: 'auth-code',
      code: new URL(location).searchParams.get('code'),
    }, '*');
  },
  startAuthFlow(sender, clientId) {
    location.href = 'https://github.com/login/oauth/authorize'
        + '?client_id=' + encodeURIComponent(clientId)
        + '&redirect_uri=' + encodeURIComponent(location.href);
  },
});
window.addEventListener('message', ({ source, origin, data }) => {
  if (source !== window.opener) return;
  if (origin !== window.origin) return;
  handlers[data.cmd](source, ...data.args);
});
window.opener.postMessage({ type: 'popup-loaded' }, '*');
</script>

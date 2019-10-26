function onPoemsClicked() {
    const params = new URLSearchParams();
    const user = getAuthorization();
    params.append('id', user.id);
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onPoemsResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('GET', 'protected/poems?' + params.toString());
    xhr.send();
}

function onProfileLoad(user) {
    clearMessages();
    showContents(['profile-content', 'logout-content']);

    const userEmailSpandEl = document.getElementById('user-email');
    const userPasswordSpanEl = document.getElementById('user-password');
    const userNameSpandEl = document.getElementById('user-name');

    userEmailSpandEl.textContent = user.email;
    userPasswordSpanEl.textContent = user.password;
    userNameSpandEl.textContent = user.name;
}

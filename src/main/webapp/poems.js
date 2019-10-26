let PoemsTableEl;
let PoemsTableBodyEl;

function onPoemClicked() {
    const PoemId = this.dataset.PoemId;

    const params = new URLSearchParams();
    params.append('id', PoemId);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onPoemResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('GET', 'protected/poem?' + params.toString());
    xhr.send();
}

function appendPoem(Poem) {

    const aEl = document.createElement('a');
    aEl.textContent = Poem.title;
    aEl.href = 'javascript:void(0);';
    aEl.dataset.PoemId = Poem.id;
    aEl.addEventListener('click', onPoemClicked);

    const trEl = document.createElement('tr');
    trEl.appendChild(aEl);
    PoemsTableBodyEl.appendChild(trEl);
}

function appendPoems(Poems) {
    removeAllChildren(PoemsTableBodyEl);

    for (let i = 0; i < Poems.length; i++) {
        const Poem = Poems[i];
        appendPoem(Poem);
    }
}

function onPoemsLoad(Poems) {
    PoemsTableEl = document.getElementById('poems');
    PoemsTableBodyEl = PoemsTableEl.querySelector('tbody');

    appendPoems(Poems);
}

function onPoemsResponse() {
    if (this.status === OK) {
        showContents(['back-to-profile-content', 'logout-content', 'poems-content']);
        onPoemsLoad(JSON.parse(this.responseText));
    } else {
        onOtherResponse(poemsContentDivEl, this);
    }
}
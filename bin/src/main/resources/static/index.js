let state = '';
let cokeCount = 0;
let pepsiCount = 0;
let message = '';

const stateElement = document.getElementById('state');
const pepsiCountElement = document.getElementById('pepsiCount');
const cokeCountElement = document.getElementById('cokeCount');
const messageElement = document.getElementById("message")

const fetchState = async () => {
    try {
        const response = await fetch('/api/soda-machine/state');
        state = await response.text();
        stateElement.textContent = state;
    } catch (error) {
        console.error('Error fetching state:', error);
    }
};

const fetchSodaCount = async () => {
    try {
        const response = await fetch('/api/soda-machine/soda-count');
        const sodaCounts = await response.json();
        cokeCount = sodaCounts.Coke || 0;
        pepsiCount = sodaCounts.Pepsi || 0;

        cokeCountElement.textContent = cokeCount;
        pepsiCountElement.textContent = pepsiCount;
    } catch (error) {
        console.error('Error fetching soda count:', error);
    }
};

const insertQuarter = async () => {
    try {
        const response = await fetch('/api/soda-machine/insert-quarter', {
            method: 'POST',
        });
        message = await response.text();
        messageElement.textContent = message;
        await fetchState();
        await fetchSodaCount();
    } catch (error) {
        console.error('Error inserting quarter:', error);
    }
};

const removeQuarter = async () => {
    try {
        const response = await fetch('/api/soda-machine/remove-quarter', {
            method: 'POST',
        });
        message = await response.text();
        messageElement.textContent = message;
        await fetchState();
        await fetchSodaCount();
    } catch (error) {
        console.error('Error removing quarter:', error);
    }
};

const pushButton = async (sodaName) => {
    try {
        const response = await fetch(`/api/soda-machine/push-button?sodaName=${encodeURIComponent(sodaName)}`, {
            method: 'POST',
        });
        message = await response.text();
        messageElement.textContent = message;
        await fetchState();
        await fetchSodaCount();
    } catch (error) {
        console.error('Error pushing button:', error);
    }
};

const insertQuarterButton = document.getElementById('insertQuarterButton');
insertQuarterButton.addEventListener('click', insertQuarter);

const removeQuarterButton = document.getElementById('removeQuarterButton');
removeQuarterButton.addEventListener('click', removeQuarter);

const buyCokeButton = document.getElementById('buyCokeButton');
buyCokeButton.addEventListener('click', () => pushButton('Coke'));

const buyPepsiButton = document.getElementById('buyPepsiButton');
buyPepsiButton.addEventListener('click', () => pushButton('Pepsi'));

fetchState();
fetchSodaCount();
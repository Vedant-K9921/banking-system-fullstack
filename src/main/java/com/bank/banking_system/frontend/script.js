const userId = localStorage.getItem("userId");

async function login() {

    const email =
        document.getElementById("email").value;

    const password =
        document.getElementById("password").value;

    const response = await fetch(
        "http://localhost:8080/api/users/login",
        {
            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                email,
                password
            })
        }
    );

    const data = await response.json();

    console.log(data);

    if (data != null) {

        localStorage.setItem(
            "userId",
            data.id
        );

        alert("Login Successful");

        window.location.href =
            "dashboard.html";

    } else {

        alert("Invalid Credentials");
    }
}

async function register() {

    const name =
        document.getElementById("name").value;

    const email =
        document.getElementById("email").value;

    const password =
        document.getElementById("password").value;

    const response = await fetch(
        "http://localhost:8080/api/users/register",
        {
            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                name,
                email,
                password
            })
        }
    );

    const data = await response.json();

    alert("Registration Successful");

    window.location.href = "login.html";
}

async function getBalance() {

    const response = await fetch(
        `http://localhost:8080/api/account/balance/${userId}`
    );

    const data = await response.text();

    document.getElementById(
        "balance"
    ).innerText = data;
}

async function deposit() {

    const amount =
        document.getElementById(
            "depositAmount"
        ).value;

    const response = await fetch(
        "http://localhost:8080/api/account/deposit",
        {
            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                userId,
                amount
            })
        }
    );

    const data = await response.text();

    alert(data);

    getBalance();

    loadTransactions();
}

async function withdrawMoney() {

    const amount =
        document.getElementById(
            "withdrawAmount"
        ).value;

    const response = await fetch(
        "http://localhost:8080/api/account/withdraw",
        {
            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                userId,
                amount
            })
        }
    );

    const data = await response.text();

    alert(data);

    getBalance();

    loadTransactions();
}

async function transferMoney() {

    const receiverId =
        document.getElementById(
            "receiverId"
        ).value;

    const amount =
        document.getElementById(
            "transferAmount"
        ).value;

    const response = await fetch(
        "http://localhost:8080/api/account/transfer",
        {
            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                senderId: userId,
                receiverId,
                amount
            })
        }
    );

    const data = await response.text();

    alert(data);

    getBalance();

    loadTransactions();
}

function logout() {

    localStorage.clear();

    window.location.href =
        "login.html";
}

async function loadTransactions() {

    const response = await fetch(
        `http://localhost:8080/api/transactions/${userId}`
    );

    const data = await response.json();

    let rows = "";

    data.forEach(t => {

        rows += `
            <tr>
                <td>${t.id}</td>
                <td>${t.type}</td>
                <td>₹${t.amount}</td>
            </tr>
        `;
    });

    document.getElementById(
        "transactionTable"
    ).innerHTML = rows;
}

if(window.location.pathname.includes(
    "dashboard.html"
)) {

    getBalance();

    loadTransactions();
}
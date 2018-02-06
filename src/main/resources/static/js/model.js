class Model {
    constructor() {
        this.templateLoaded = new AppEvent(this);
        this.logInEvent = new AppEvent(this);
        this.registeredEvent = new AppEvent(this);

        this.currentTrip = {};
        this.user = {};
        this.isLogged = false;
    }

    setTrip(trip) {
        this.currentTrip = trip;
    }

    getTemplate(url) {
        fetch(url)
        .then(this.checkStatus)
        .then(response => {
            return response.text();
        })
        .then(data => {
            this.templateLoaded.notify(data);
        })
        .catch(function (error) {
            alert(error);
        });
    }

    checkStatus(response) {
        if (response.status === 200) {
            return Promise.resolve(response);
        } else {
            return Promise.reject(new Error(response.statusText));
        }
    }

    sendReservation(data) {
        const url = '/reservation/book';

        try {
            data = JSON.stringify(data);
        } catch(error) {
            console.error(error);
            alert('Invalid reservation JSON format');
            return;
        }

        fetch(url, {
            method: 'POST',
            body: data,
            headers: {'Content-Type': 'application/json'},
        })
        .then(response => console.log(response))
        .catch(error => alert(error));
    }

    authorizeUser(user) {
        const url = `/user/log?email=${user.email}&password=${user.password}`;

        fetch(url)
        .then(this.checkStatus)
        .then(response => {
            return response.json();
        })
        .then(data => {
            if (data.user) {
                this.logIn(data.user);
            } else {
                throw new Error(data.status);
            }
        })
        .catch(error => alert(error));
    }

    logIn(user) {
        this.isLogged = true;
        this.user = user;
        this.logInEvent.notify(this.isLogged);
    }

    registerUser(user) {
        const url = '/user/register';

        try {
            user = JSON.stringify(user);
        } catch (error) {
            console.error(error);
            alert('register user JSON format invalid');
            return;
        }

        fetch(url, {
            method: 'POST',
            body: user,
            headers: {'Content-Type': 'application/json'}
        })
        .then(response => {
            if (response.status === 201) {
                this.registeredEvent.notify();
            } else {
                return Promise.reject(response.statusText);
            }
        })
        .catch(error => alert(error));
    }
}

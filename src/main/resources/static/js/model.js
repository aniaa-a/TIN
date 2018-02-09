class Model {
    constructor() {
        this.templateLoaded = new AppEvent(this);
        this.logInEvent = new AppEvent(this);
        this.registeredEvent = new AppEvent(this);
        this.reservationEvent = new AppEvent(this);

        this.currentTrip = {};
        this.user = {};
        this.isLogged = false;
    }

    setTrip(trip) {
        this.currentTrip = trip;
    }

    getTemplate(url) {
        fetch(url)
        .then(response => this.checkStatus(response, 200))
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

    sendReservation(data) {
        const url = '/reservation/book';

        data = JSON.stringify(data);

        fetch(url, {
            method: 'POST',
            body: data,
            headers: {'Content-Type': 'application/json'},
        })
        .then(response => this.checkStatus(response, 201))
        .then(() => {
            this.reservationEvent.notify();
        })
        .catch(error => alert(error));
    }

    authorizeUser(user) {
        const url = `/user/log?email=${user.email}&password=${user.password}`;

        fetch(url)
        .then(response => this.checkStatus(response, 200))
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

    checkIfLogged(){
        const url = '/user/isLogged';

        fetch(url)
            .then(response => this.checkStatus(response, 200))
            .then(response => response.json())
            .then(data => console.log(data))
            .catch(error => console.error(error));
    }

    registerUser(user) {
        const url = '/user/register';

        user = JSON.stringify(user);

        fetch(url, {
            method: 'POST',
            body: user,
            headers: {'Content-Type': 'application/json'}
        })
        .then(response => {
            if (response.status === 201) {
                this.registeredEvent.notify();
            } else if (response.status === 200) {
                alert('Podany adres email jest ju\u017C zaj\u0119ty')
            } else {
                return Promise.reject(response.statusText);
            }
        })
        .catch(error => alert(error));
    }

    checkStatus(response, status) {
        if (response.status === status) {
            return Promise.resolve(response);
        } else {
            return Promise.reject(new Error(response.statusText));
        }
    }
}

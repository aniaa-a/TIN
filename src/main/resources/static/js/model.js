class Model {
    constructor() {
        this.user = {};
        this.authorizationInfo = {
            isLogged: false,
            isAdmin: false
        };

        this.templateLoaded = new AppEvent(this);
        this.authorizationEvent = new AppEvent(this);
    }

    getTemplate(url) {
        fetch(url)
            .then(response => Model.checkStatus(response, 200))
            .then(response => response.text())
            .then(data => this.templateLoaded.notify(data))
            .catch(error => alert(error));
    }

    getUser() {
        return this.user;
    }

    authorizeUser(user) {
        const url = `/user/log?email=${user.email}&password=${user.password}`;

        fetch(url, {credentials: 'same-origin'})
            .then(response => Model.checkStatus(response, 200))
            .then(response => response.json())
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
        this.authorizationInfo.isLogged = true;
        this.authorizationInfo.isAdmin = user.role === 'admin';
        this.user = user;
        this.authorizationEvent.notify(this.authorizationInfo);
    }

    logOut() {
        const url = '/user/logout';

        fetch(url, {method: 'POST', credentials: 'same-origin'})
            .then(response => Model.checkStatus(response, 200))
            .then(() => {
                this.authorizationInfo.isLogged = false;
                this.authorizationInfo.isAdmin = false;
                this.user = null;
                location.hash = '/home';
                this.authorizationEvent.notify(this.authorizationInfo);
            })
            .catch(error => alert(error));
    }

    checkIfLogged() {
        const url = '/user/isLogged';

        fetch(url, {credentials: 'same-origin'})
            .then(response => Model.checkStatus(response, 200))
            .then(response => response.json())
            .then(data => {
                if (data.status === 'ok') {
                    this.logIn(data.user);
                } else {
                    this.authorizationEvent.notify(this.authorizationInfo);
                }
            })
            .catch(error => {
                this.authorizationEvent.notify(this.authorizationInfo);
                alert(error);
            });
    }

    registerUser(user) {
        const url = '/user/register';

        fetch(url, {method: 'POST', body: JSON.stringify(user), headers: {'Content-Type': 'application/json'}})
            .then(response => {
                if (response.status === 201) {
                    this.authorizeUser(user);
                } else if (response.status === 200) {
                    alert('Podany adres email jest ju\u017C zaj\u0119ty')
                } else {
                    return Promise.reject(response.statusText);
                }
            })
            .catch(error => alert(error));
    }

    static checkStatus(response, status) {
        if (response.status === status) {
            return Promise.resolve(response);
        } else {
            return Promise.reject(new Error(response.statusText));
        }
    }
}

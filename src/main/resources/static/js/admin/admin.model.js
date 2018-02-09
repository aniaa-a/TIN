class AdminModel {
    constructor() {
        this.reservations = [];
        this.cars = [];
        this.drivers = [];
        this.currentEdit = null;
    }

    getReservations(callback) {
        const url = '/reservation/getAll';

        fetch(url)
        .then(response => this.checkStatus(response, 200))
        .then(response => response.json())
        .then(data => {
            this.reservations = data;
            callback(this.reservations);
        })
        .catch(error => alert(error));
    }

    getDrivers(callback) {
        const url = '/driver/getAll';

        fetch(url)
            .then(response => this.checkStatus(response, 200))
            .then(response => response.json())
            .then(data => {
                this.drivers = data.listCarDriver;
                callback(this.drivers);
            })
            .catch(error => alert(error));
    }

    getCars(callback) {
        const url = '/car/getAll';

        fetch(url)
            .then(response => this.checkStatus(response, 200))
            .then(response => response.json())
            .then(data => {
                this.cars = data.carList;
                callback(this.cars);
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

class AdminEditPopup {
    constructor (selector) {
        this.element = document.querySelector(selector);
        this.cancel = this.element.querySelector('#cancelEdit');
        this.form = this.element.querySelector('#editForm');
        this.cancel.addEventListener('click', this.hide.bind(this));
    }

    show() {
        this.element.hidden = false;
    }

    hide() {
        this.element.hidden = true;
        this.form.reset();
    }

    setCars(cars) {
        let html = '';

        for (let car of cars) {
            html += `<option value="${car.carId}">${car.brand} | ilość miejsc: ${car.carSeats}</option>`;
        }
        this.form.cars.innerHTML = html;
    }

    setDrivers(drivers) {
        let html = '';

        for (let driver of drivers) {
            html += `<option value="${driver.carDriverId}">${driver.name} ${driver.surname}</option>`
        }
        this.form.drivers.innerHTML = html;
    }
}

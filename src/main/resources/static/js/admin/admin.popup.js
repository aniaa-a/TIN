class AdminEditPopup {
    constructor(selector) {
        this.element = document.querySelector(selector);
        this.cancel = this.element.querySelector('#cancelEdit');
        this.form = this.element.querySelector('#editForm');
        this.cancel.addEventListener('click', this.hide.bind(this));
    }

    show(reservation) {  //przy edytuj
        const driverOptions = Array.from(this.form.drivers.children);
        const carOptions = Array.from(this.form.cars.children);

        driverOptions.forEach(driver => {
            if (driver.value === ('' + reservation.driverId)) {
                driver.selected = true;
            }
        });

        carOptions.forEach(car => {
            if (car.value === ('' + reservation.carId)) {
                car.selected = true;
            }
        });

        this.element.hidden = false;
    }

    hide() {
        this.element.hidden = true;
        this.form.reset();
    }

    setCars(cars) {
        let html = '<option></option>';

        for (let car of cars) {
            html += `<option value="${car.carId}">${car.brand} | ilość miejsc: ${car.carSeats}</option>`;
        }
        this.form.cars.innerHTML = html;
    }

    setDrivers(drivers) {
        let html = '<option></option>';

        for (let driver of drivers) {
            html += `<option value="${driver.carDriverId}">${driver.name} ${driver.surname}</option>`
        }

        this.form.drivers.innerHTML = html;
    }
}

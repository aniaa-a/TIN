class AdminService {
    constructor() {}

    getTableTemplate(reservations, drivers, cars) {
        const table = document.createElement('table');

        table.id = 'reservationsTable';
        table.innerHTML = this.tableHeaderTemplate();

        for (let reservation of reservations) {
            let tr = document.createElement('tr'),
                driver = this.getDriver(reservation.driverId, drivers),
                car = this.getCar(reservation.carId, cars),
                edit = `<button type="button" data-role="edit" data-reservation="${reservation.idReservation}">Edytuj</button>`,
                remove = `<button type="button" data-role="remove" data-reservation="${reservation.idReservation}">Kasuj</button>`;

            tr.id = reservation.idReservation;
            tr.appendChild(this.cellTemplate('email', reservation.email));
            tr.appendChild(this.cellTemplate('city', reservation.city));
            tr.appendChild(this.cellTemplate('date', reservation.dateTrip));
            tr.appendChild(this.cellTemplate('driver', driver));
            tr.appendChild(this.cellTemplate('car', car));
            tr.appendChild(this.cellTemplate('status', reservation.status));
            tr.appendChild(this.cellTemplate('cell-edit', edit));
            tr.appendChild(this.cellTemplate('cell-remove', remove));
            table.appendChild(tr);
        }

        return table;
    }

    getDriver(idDriver, drivers) {
        let driver = drivers.filter(driver => driver.carDriverId === idDriver)[0];

        if (driver) {
            return `${driver.name} ${driver.surname}`;
        } else {
            return 'Brak'
        }
    }

    getCar(idCar, cars) {
        let car = cars.filter(car => car.carId === idCar)[0];

        if (car) {
            return `${car.brand} | ${car.registrationNum}`;
        } else {
            return 'Brak'
        }
    };

    tableHeaderTemplate() {
        return `
            <tr>
                <th>E-mail</th>
                <th>Miasto</th>
                <th>Data</th>
                <th>Kierowca</th>
                <th>Samochód</th>
                <th>Status</th>
            </tr>`
    }

    cellTemplate(className, content) {
        let td = document.createElement('td');

        td.className = className;
        td.innerHTML = content;

        return td;
    }
}

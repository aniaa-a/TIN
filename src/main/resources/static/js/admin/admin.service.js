class AdminService {
    constructor() {}

    getTableTemplate(reservations) {
        const table = document.createElement('table');

        table.id = 'reservationsTable';
        table.innerHTML = this.tableHeaderTemplate();

        for (let reservation of reservations) {
            let tr = document.createElement('tr'),
                button = `<button type="button" data-role="edit" data-reservation="${reservation.idReservation}">Edytuj</button>`;

            tr.id = reservation.idReservation;
            tr.appendChild(this.cellTemplate('email', reservation.email));
            tr.appendChild(this.cellTemplate('city', reservation.city));
            tr.appendChild(this.cellTemplate('date', reservation.dateTrip));
            tr.appendChild(this.cellTemplate('driver', reservation.driver || 'Brak'));
            tr.appendChild(this.cellTemplate('car', reservation.car || 'Brak'));
            tr.appendChild(this.cellTemplate('status', reservation.status));
            tr.appendChild(this.cellTemplate('cell-edit', button));
            table.appendChild(tr);
        }

        return table;
    }

    tableHeaderTemplate() {
        return `
            <tr>
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

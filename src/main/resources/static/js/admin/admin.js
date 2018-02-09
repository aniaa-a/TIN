(function() {
    const admin = new AdminModel();
    const service = new AdminService();
    const popup = new AdminEditPopup('.popup');
    const editForm = document.querySelector('#editForm');

    admin.getData();
    admin.getDataEvent.attach(setTable);


    editForm.addEventListener('submit', function(e) {
        e.preventDefault();

        let reservationDate = admin.findReservationById(admin.currentEdit).dateTrip;
        let body = {
            idCar: this.cars.value,
            idCarDriver: this.drivers.value,
            date: reservationDate,
            idReservation: admin.currentEdit
        };

        admin.updateReservation(JSON.stringify(body));
    });

    function setTable() {
        const container = document.querySelector('#container');
        const table = service.getTableTemplate(admin.reservations, admin.drivers, admin.cars);

        removeTable();
        container.appendChild(table);
        popup.setDrivers(admin.drivers);
        popup.setCars(admin.cars);
        bindClickEvent();
        popup.hide();
    }

    function removeTable() {
        const table = document.querySelector('#reservationsTable');

        if (table) {
            table.parentElement.removeChild(table);
        }
    }

    function bindClickEvent() {
        document.addEventListener('click', function(event) {
            if (event.target.dataset.role === 'edit') {
                const reservationId = event.target.dataset.reservation;

                admin.currentEdit = reservationId;
                popup.show(admin.findReservationById(reservationId));
            } else if (event.target.dataset.role === 'remove') {
                const reservationId = event.target.dataset.reservation;

                admin.deleteReservation(reservationId);
            }
        })
    }
})();

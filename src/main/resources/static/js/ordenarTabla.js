document.addEventListener('DOMContentLoaded', function () {
    const table = document.querySelector('table');
    const headers = table.querySelectorAll('th.sortable');
    
    // Función para ordenar las filas
    function sortTable(columnIndex, isAscending) {
        const rows = Array.from(table.querySelectorAll('tbody tr')); // Obtener solo las filas del tbody
        const sortedRows = rows.sort((rowA, rowB) => {
            const cellA = rowA.cells[columnIndex].innerText.trim();
            const cellB = rowB.cells[columnIndex].innerText.trim();

            const a = isNaN(cellA) ? cellA : parseFloat(cellA);
            const b = isNaN(cellB) ? cellB : parseFloat(cellB);

            if (a < b) {
                return isAscending ? -1 : 1;
            } else if (a > b) {
                return isAscending ? 1 : -1;
            }
            return 0;
        });

        // Reaplicar las filas ordenadas al tbody
        sortedRows.forEach(row => table.querySelector('tbody').appendChild(row));
    }

    // Agregar el evento de clic a cada cabecera
    headers.forEach((header, index) => {
        const icon = header.querySelector('i');
        
        header.addEventListener('click', function () {
            const isAscending = header.classList.contains('ascending');
            
            // Alternar entre ascendente y descendente
            headers.forEach(h => {
                h.classList.remove('ascending', 'descending');
                h.querySelector('i').classList.remove('bi-arrow-up', 'bi-arrow-down', 'text-warning');
            });

            if (isAscending) {
                header.classList.add('descending');
                icon.classList.add('bi-arrow-down', 'text-warning'); // Flecha hacia abajo amarilla
                icon.classList.remove('bi-arrow-up');
                sortTable(index, false);
            } else {
                header.classList.add('ascending');
                icon.classList.add('bi-arrow-up', 'text-warning'); // Flecha hacia arriba amarilla
                icon.classList.remove('bi-arrow-down');
                sortTable(index, true);
            }
        });
    });
});


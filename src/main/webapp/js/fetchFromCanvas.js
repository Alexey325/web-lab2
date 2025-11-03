canvas.addEventListener('click', (event) => {

    const rInput = document.querySelector('input[name="r_input"]:checked')

    if ( rInput!== null ) {
        const rect = canvas.getBoundingClientRect();

        const canvasX = event.clientX - rect.left;
        const canvasY = event.clientY - rect.top;

        const xValue = (canvasX - centerX) / 160 * rInput.value
        const yValue = - (canvasY - centerY) / 160 * rInput.value

        // console.log(xValue, yValue)

        window.location.href = `./main?x_input=${xValue}&y_input=${yValue}&r_input=${rInput.value}`;

    } else {

        const errorElement = document.getElementById('r-errors');
        errorElement.textContent = 'Для использования интерактивного графа необходимо выбрать R';
    }

});




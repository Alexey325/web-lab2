const canvas = document.getElementById('canvas');
const ctx = canvas.getContext('2d');

const centerX = canvas.width / 2;
const centerY = canvas.height / 2;

// ось X
ctx.beginPath();
ctx.moveTo(0, centerY);        // Начинаем слева
ctx.lineTo(canvas.width, centerY); // Рисуем до правого края
ctx.stroke();

// ось Y
ctx.beginPath();
ctx.moveTo(centerX, 0);         // Начинаем сверху
ctx.lineTo(centerX, canvas.height); // Рисуем до низа
ctx.stroke();

// стрелки на оси X
ctx.beginPath();
ctx.moveTo(canvas.width - 10, centerY - 5);
ctx.lineTo(canvas.width, centerY);
ctx.lineTo(canvas.width - 10, centerY + 5);
ctx.stroke();

// стрелки на оси Y
ctx.beginPath();
ctx.moveTo(centerX - 5, 10);
ctx.lineTo(centerX, 0);
ctx.lineTo(centerX + 5, 10);
ctx.stroke();

// оси
ctx.font = '16px Arial';
ctx.fillText('X', canvas.width - 15, centerY - 10);
ctx.fillText('Y', centerX + 10, 15);
ctx.fillText('0', centerX - 15, centerY + 15);


// черточки
ctx.beginPath();
ctx.moveTo(centerX + canvas.width / 5, centerY - canvas.height / 100);
ctx.lineTo(centerX + canvas.width / 5, centerY + canvas.height / 100);
ctx.stroke();

ctx.beginPath();
ctx.moveTo(centerX - canvas.width / 5, centerY - canvas.height / 100);
ctx.lineTo(centerX - canvas.width / 5, centerY + canvas.height / 100);
ctx.stroke();

ctx.beginPath();
ctx.moveTo(centerX + 2 * canvas.width / 5, centerY - canvas.height / 100);
ctx.lineTo(centerX + 2 * canvas.width / 5, centerY + canvas.height / 100);
ctx.stroke();

ctx.beginPath();
ctx.moveTo(centerX - 2 * canvas.width / 5, centerY - canvas.height / 100);
ctx.lineTo(centerX - 2 * canvas.width / 5, centerY + canvas.height / 100);
ctx.stroke();

ctx.beginPath();
ctx.moveTo(centerX + canvas.width / 100, centerY + canvas.height / 5);
ctx.lineTo(centerX - canvas.width / 100, centerY + canvas.height / 5);
ctx.stroke();

ctx.beginPath();
ctx.moveTo(centerX + canvas.width / 100, centerY - canvas.height / 5);
ctx.lineTo(centerX - canvas.width / 100, centerY - canvas.height / 5);
ctx.stroke();

ctx.beginPath();
ctx.moveTo(centerX + canvas.width / 100, centerY + 2 * canvas.height / 5);
ctx.lineTo(centerX - canvas.width / 100, centerY + 2 * canvas.height / 5);
ctx.stroke();

ctx.beginPath();
ctx.moveTo(centerX + canvas.width / 100, centerY - 2 * canvas.height / 5);
ctx.lineTo(centerX - canvas.width / 100, centerY - 2 * canvas.height / 5);
ctx.stroke();

// R
ctx.font = '16px Arial';

ctx.fillText('R/2', centerX + canvas.width / 5 - 10, centerY - canvas.height / 100 - 5);
ctx.fillText('-R/2', centerX - canvas.width / 5 - 10, centerY - canvas.height / 100 - 5);
ctx.fillText('R', centerX + 2 * canvas.width / 5 - 5, centerY - canvas.height / 100 - 5);
ctx.fillText('-R', centerX - 2 * canvas.width / 5 - 5, centerY - canvas.height / 100 - 5);
ctx.fillText('R/2', centerX + canvas.width / 100 + 5, centerY - canvas.height / 5  + 5);
ctx.fillText('-R/2', centerX + canvas.width / 100 + 5, centerY + canvas.height / 5  + 5);
ctx.fillText('R', centerX + canvas.width / 100 + 5, centerY - 2 * canvas.height / 5  + 5);
ctx.fillText('-R', centerX + canvas.width / 100 + 5, centerY + 2 * canvas.height / 5  + 5);

// фигуры
ctx.beginPath();
ctx.moveTo(centerX, centerY);
ctx.lineTo(centerX, centerY - 2 * canvas.width / 5);
ctx.lineTo(centerX + 2 * canvas.width / 5, centerY - 2 * canvas.width / 5);
ctx.lineTo(centerX + 2 * canvas.width / 5, centerY);
ctx.lineTo(centerX, centerY);
ctx.closePath();

ctx.fillStyle = 'rgba(173, 216, 230, 0.45)';
ctx.fill();
ctx.strokeStyle = '#4682B4';
ctx.stroke();

ctx.beginPath();
ctx.moveTo(centerX, centerY);
ctx.arc(centerX, centerY, 2 * canvas.width / 5, Math.PI / 2, Math.PI);

ctx.fill();
ctx.stroke();

ctx.beginPath();
ctx.moveTo(centerX, centerY);
ctx.lineTo(centerX, centerY + 2 * canvas.width / 5);
ctx.lineTo(centerX + canvas.width / 5, centerY);
ctx.lineTo(centerX, centerY);
ctx.closePath();

ctx.fill();
ctx.stroke();


function drawPoint(x, y, r, isHit) {
    ctx.fillStyle = (isHit === "ДА") ? 'green' : 'red';
    ctx.beginPath();
    ctx.arc(centerX + x/r * 160, centerY - y/r * 160, 5, 0, 2 * Math.PI);
    ctx.fill();

}



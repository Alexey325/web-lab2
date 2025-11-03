class FormValidation {
    selectors = {
        form: "[data-js-form]",
        fieldErrors: "[data-js-form-field-errors]"
    }

    constructor() {
        this.BindEvents()
    }

    manageErrors(fieldControlElement, errorMessage) {
        const fieldErrorsElement = fieldControlElement.parentElement.querySelector(this.selectors.fieldErrors)
        fieldErrorsElement.textContent = errorMessage || ""
    }

    errorMessages = {
        valueMissing: "Заполните поле",
        rangeUnderflow: "Число меньше минимального значения",
        rangeOverflow: "Число больше максимального значения",
    }

    validateField(fieldControlElement) {
        const errors = fieldControlElement.validity
        let errorMessage = ""

        const value = parseFloat(fieldControlElement.value)
        if (value === -5 || value === 5) {
            errorMessage = "Значения -5 и 5 недопустимы"
            this.manageErrors(fieldControlElement, errorMessage)
            return
        }

        for (const [errorType, message] of Object.entries(this.errorMessages)) {
            if (errors[errorType]) {
                errorMessage = message
                break
            }
        }

        this.manageErrors(fieldControlElement, errorMessage)
    }

    onBlur(event) {
        const target = event.target
        if (target.type === 'number' || target.type === 'text') {
            this.validateField(target)
        }
    }

    // onKeyDown(event) {
    //
    //     if (event.code === 'Enter') {
    //         event.preventDefault()
    //     }
    // }

    BindEvents() {

        document.addEventListener('blur', (event) => {
            this.onBlur(event)
        }, true)


        // document.addEventListener('keydown', (event) => {
        //     const inForm = event.target && event.target.closest && event.target.closest(this.selectors.form)
        //     if (inForm) this.onKeyDown(event)
        // }, true)

    }

}

new FormValidation()
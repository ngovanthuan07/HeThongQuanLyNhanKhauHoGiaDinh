export function GetDataForm(formSelector) {
    const formElement = document.querySelector(formSelector);
    const enableInputs = formElement.querySelectorAll("[name]");
    const formValues = Array.from(enableInputs).reduce(function (values, input) {
            switch (input.type) {
                case "radio":
                    values[input.name] = formElement.querySelector(
                        'input[name="' + input.name + '"]:checked'
                    ).value;
                    break;
                case "checkbox":
                    if (!input.matches(":checked")) {
                        values[input.name] = "";
                        return values;
                    }
                    if (!Array.isArray(values[input.name])) {
                        values[input.name] = [];
                    }
                    values[input.name].push(input.value);
                    break;
                case "file":
                    values[input.name] = input.files;
                    break;
                default:
                    values[input.name] = input.value;
            }
            return values;
        }, {});
    return formValues
}


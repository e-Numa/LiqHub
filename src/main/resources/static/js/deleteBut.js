document.addEventListener("DOMContentLoaded", function () {
    const deleteButtons = document.querySelectorAll(".delete-button");

    deleteButtons.forEach((button) => {
        button.addEventListener("click", function () {
            const productId = button.getAttribute("data-product-id");

            if (confirm("Are you sure you want to delete this product?")) {
                fetch(`/products/{product_id}`, {
                    method: "DELETE",
                })
                    .then((response) => {
                        if (response.ok) {
                            alert("Product deleted successfully");
                            // Optionally, remove the row from the table
                            button.closest("tr").remove();
                        } else {
                            alert("Failed to delete the product");
                        }
                    })
            }
        });
    });
});

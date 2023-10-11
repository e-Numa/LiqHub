const menu = document.querySelector('#menu-bar');
const navbar = document.querySelector('.navbar');
const header2 = document.querySelector('.secondHeader');
const hamburger = document.querySelector('.fa-bars');
const closed = document.querySelector('.fa-window-close');

const products = [
    { name: "Singleton", price: 21000.00, quantity: 0 },
    { name: "Black Label", price: 22000.00, quantity: 0 },
    { name: "Jack Daniel", price: 18600.00, quantity: 0 },
    { name: "Glenmoriange", price: 120000.00, quantity: 0 },
    { name: "Glenfiddich", price: 78000.00, quantity: 0 },
    { name: "Chivas Regal", price: 21000.00, quantity: 0 }
];

// Get references to HTML elements
const cartCounterElement = document.getElementById('cart-counter');
const cartItemsElement = document.getElementById('cart-items');
const totalAmountElement = document.getElementById('total-amount');
const cartBtn = document.getElementById('cart-btn');
const cartItems = [];

// Initialize cart counter
let cartCount = 0;
cartCounterElement.innerText = cartCount;

// Add event listeners to all "Add to Cart" buttons
const addToCartButtons = document.querySelectorAll('.add-to-cart-btn');
addToCartButtons.forEach(button => {
    button.addEventListener('click', function () {
        const index = this.dataset.index;
        const product = products[index];

        if (product.quantity === 0) {
            cartItems.push(product);
            cartCount++;
            product.quantity++;
            this.innerText = `Remove from Cart`;
        } else {
            const itemIndex = cartItems.findIndex(item => item.name === product.name);
            if (itemIndex !== -1) {
                cartItems.splice(itemIndex, 1);
                cartCount--;
                product.quantity = 0;
                this.innerText = `Add to Cart`;
            }
        }

        // Update cart counter
        cartCounterElement.innerText = cartCount;
    });
});

// Function to open the cart dialog
function openCartDialog() {
    // Update the cart dialog content
    if (cartCount === 0) {
        cartItemsElement.innerHTML = "<p>Your cart is empty.</p>";
        totalAmountElement.innerText = "₦0";
    } else {
        cartItemsElement.innerHTML = "<ul>";
        let totalAmount = 0;
        for (let item of cartItems) {
            cartItemsElement.innerHTML += `
                <li>
                    ${item.name}:
                    <button class="min" onclick="decrementQuantity('${item.name}')">-</button>
                    <span>${item.quantity}</span>
                    <button class="max" onclick="incrementQuantity('${item.name}')">+</button>
                    (₦${item.price.toFixed(2)} each)
                </li>`;
            totalAmount += item.quantity * item.price;
        }
        cartItemsElement.innerHTML += "</ul>";
        totalAmountElement.innerText = `₦${totalAmount.toFixed(2)}`;
    }

    // Display the cart dialog
    document.getElementById('cartModal').style.display = 'block';
}

// Function to close the cart dialog
function closeCartDialog() {
    document.getElementById('cartModal').style.display = 'none';
}

// Function to increment quantity for a product
function incrementQuantity(productName) {
    const product = cartItems.find(item => item.name === productName);
    if (product) {
        product.quantity++;
        openCartDialog();
    }
}

// Function to decrement quantity for a product
function decrementQuantity(productName) {
    const product = cartItems.find(item => item.name === productName);
    if (product) {
        if (product.quantity > 1) {
            product.quantity--;
        } else {
            const index = cartItems.findIndex(item => item.name === productName);
            if (index !== -1) {
                cartItems.splice(index, 1);
                cartCount--;
            }
        }
        openCartDialog();
    }
}

// Function to perform checkout
function checkout() {
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const phone = document.getElementById('phone').value;

    // Perform any necessary checkout actions here
    // For demonstration purposes, we will just alert the user with the collected information.
    const cartContent = cartItems.map(item => `${item.name} (Qty: ${item.quantity})`).join('\n');
    const totalAmount = totalAmountElement.innerText.replace("₦", "");
    const checkoutMessage = `Thank you, ${name}!\n\n Kindly confirm items for checkout\n${cartContent}\n\nTotal Amount: ₦${totalAmount}\n\nWe will contact you at ${phone} or ${email} for further details.`;
    alert(checkoutMessage);

    // Clear the cart and close the cart dialog
    cartItems.length = 0;
    cartCount = 0;
    cartCounterElement.innerText = cartCount;
    cartItemsElement.innerHTML = "<p>Your cart is empty.</p>";
    totalAmountElement.innerText = "₦0";
    document.getElementById('cartModal').style.display = 'none';
}

<template>
	<v-app>
		<!-- Content, Footer -->

		<router-view />
		<Footer />
	</v-app>
</template>

<script>
	import Footer from "./views/Footer";
	export default {
		name: "App",

		components: {
			Footer
		},

		data: () => ({
			//
		})
	};
</script>

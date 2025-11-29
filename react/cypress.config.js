const { defineConfig } = require("cypress");

module.exports = defineConfig({
  video: true, // Enable video recording
  videoCompression: 32, // Compress video to save space
  e2e: {
    supportFile: false, // Explicitly disable the support file requirement
    setupNodeEvents(on, config) {
      // implement node event listeners here
    },
  },
});
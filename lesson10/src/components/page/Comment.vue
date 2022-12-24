<template>
<div class="comment">
  <div class="author">{{ user.name }}</div>
  <div class="text">{{ comment.text }}</div>
</div>
</template>

<script>
export default {
  name: "Comment",
  props: ["comment", "users"],
  computed: {
    user: function () {
      return Object.values(this.users).filter(u => u.id === this.comment.userId)[0];
    },
  }
}
</script>

<style scoped>
.comment .author {
  color: var(--caption-color);
  font-weight: bold;
  margin-bottom: 0.5rem;
}

.comment {
  margin-bottom: 1rem;
  max-width: 100%;
}
</style>